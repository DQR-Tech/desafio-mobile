//
//  Manager.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

import Foundation

enum NetworkResponse:String {
    case success
    case authenticationError = "You need to be authenticated first."
    case badRequest = "Bad request"
    case outdated = "The url you requested is outdated."
    case failed = "Network request failed."
    case noData = "Response returned with no data to decode."
    case unableToDecode = "We could not decode the response."
    case problemNetwork = "Por favor, verifique sua conexão de rede."
    
}

enum Result<String>{
    case success
    case failure(String)
}

struct NetworkManager {
    
    static let shared = NetworkManager()
    static let environment : NetworkEnvironment = .base
    let currencyApi = Router<CurrencyApi>()
    //var errorResponse: ErrorResponse = ErrorResponse(type: .errorAPI, message: "")
    
    //MASK: GET
    func getList(completion: @escaping (CurrenciesResponse?, ErrorResponse?) -> Void){
        
        currencyApi.request(.list) { data, response, error in

            if error != nil {
                completion(nil, ErrorResponse(type: .errorAPI, message: NetworkResponse.problemNetwork.rawValue))
            }
            
                
            if let response = response as? HTTPURLResponse {
                let result = self.handleNetworkResponse(response)
                                
                switch result {
                    case .success:
                        guard let responseData = data else {
                            completion(nil,ErrorResponse(type: .errorApp, message: NetworkResponse.noData.rawValue))
                            return
                        }
                        do {
                            let json = try JSONDecoder().decode(CurrenciesResponse.self, from: responseData)
                            if(!json.success){
                                completion(nil, ErrorResponse(type: .errorAPI, message: NetworkResponse.authenticationError.rawValue))
                            }
                            
                            completion(json, nil)
                        }catch {
                            print(error)
                            completion(nil, ErrorResponse(type: .errorApp, message: NetworkResponse.unableToDecode.rawValue))
                        }
                    case .failure(let networkFailureError):
                        completion(nil, ErrorResponse(type: .errorAPI, message: networkFailureError))
                }
            }
        }
    }
    
    
    //MASK: GET
    func getLive(completion: @escaping (QuotesResponse?, ErrorResponse?) -> Void){
        
        currencyApi.request(.live) { data, response, error in
            if error != nil {
                completion(nil, ErrorResponse(type: .errorAPI, message: NetworkResponse.problemNetwork.rawValue))
            }
                
            if let response = response as? HTTPURLResponse {
                let result = self.handleNetworkResponse(response)
                                
                switch result {
                case .success:
                    guard let responseData = data else {
                        completion(nil,ErrorResponse(type: .errorAPI, message: NetworkResponse.noData.rawValue))
                        return
                    }

                    do {
                        let json = try JSONDecoder().decode(QuotesResponse.self, from: responseData)
                        if(!json.success){
                            completion(nil, ErrorResponse(type: .errorAPI, message: NetworkResponse.authenticationError.rawValue))
                        }
                        completion(json, nil)
                    }catch {
                        print(error)
                        completion(nil, ErrorResponse(type: .errorApp, message: NetworkResponse.unableToDecode.rawValue))
                    }
                case .failure(let networkFailureError):
                    completion(nil, ErrorResponse(type: .errorAPI, message: networkFailureError))
                }
            }
        }
    }
    
    
    fileprivate func handleNetworkResponse(_ response: HTTPURLResponse) -> Result<String>{
        switch response.statusCode {
        case 200...299: return .success
        case 401...500: return .failure(NetworkResponse.authenticationError.rawValue)
        case 501...599: return .failure(NetworkResponse.badRequest.rawValue)
        case 600: return .failure(NetworkResponse.outdated.rawValue)
        default: return .failure(NetworkResponse.failed.rawValue)
        }
    }
}
