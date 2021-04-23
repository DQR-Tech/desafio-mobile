//
//  CurrencyEndpoint.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

import Foundation

enum NetworkEnvironment {
    case base
}

public enum CurrencyApi {
    case list
    case live
}

extension CurrencyApi: EndPointType {
    
    private enum Constants {
        static let acessKey = "9ea7d3641517fd812e50ee4430c629c7"
    }
    
    var environmentBaseURL : String {
        switch NetworkManager.environment {
            case .base: return "http://api.currencylayer.com/"
        }
    }
    
    var baseURL: URL {
        guard let url = URL(string: environmentBaseURL) else { fatalError("baseURL could not be configured.")}
        return url
    }
    
    var path: String {
        switch self {
            case .list:
                return "list"
            case .live:
                return "live"
        }
    }
    
    var httpMethod: HTTPMethod {
        switch self {
            case .list:
                return .get
            case .live:
                return .get
        }
    }
    
    var task: HTTPTask {
        switch self {
            case .list:
                return .requestParameters(bodyParameters:nil,bodyEncoding: .urlEncoding, urlParameters: ["access_key": Constants.acessKey])
            case .live:
                return .requestParameters(bodyParameters:nil,bodyEncoding: .urlEncoding, urlParameters: ["access_key": Constants.acessKey])
                
        }
    }
    
    var headers: HTTPHeaders? {
        return nil
    }
    
}
