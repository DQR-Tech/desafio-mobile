//
//  Provider.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import Foundation

class Provider {
    
    static let apiKey = "b9ab56258a9c7c740f0cc1182856ef13"
    static let baseURL = "http://apilayer.net/api/"
    
    static var quotes: Quotes? = nil

    static func getLatestQuotes(completion: @escaping(Quotes) -> Void) {
        
        let parameter = "live"
        let urlString = "\(baseURL)/\(parameter)?access_key=\(apiKey)"
        
        guard let quotesUrl = URL(string: urlString) else { return }
     
        let urlRequest = URLRequest(url: quotesUrl)
        let task = URLSession.shared.dataTask(with: urlRequest, completionHandler: { (data, response, error) -> Void in
     
            if let error = error {
                print(error)
                return
            }
     
            // Parse JSON data
            if let data = data {
                do {
                    let quotesResponse = try JSONDecoder().decode(QuotesResponse.self, from: data)
                    let timestamp = quotesResponse.timestamp
                    let quotesList = quotesResponse.quotes.map {Quote(unit: $0.key, rate: $0.value)}
                    let quotes = Quotes(timestamp: timestamp, quotes: quotesList)
                    Provider.quotes = quotes
                    completion(quotes)
                } catch {
                    print(error.localizedDescription)
                }
            }
        })
        task.resume()
    }
}

