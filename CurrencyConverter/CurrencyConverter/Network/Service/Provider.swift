//
//  Provider.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import Foundation

open class Provider {
    
    static let apiKey = "b9ab56258a9c7c740f0cc1182856ef13"
    static let baseURL = "http://apilayer.net/api/"
        
    static func getLatestQuotes(completion: @escaping(QuotesResults) -> Void) {
        
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
                    
                    let latestTimestamp = quotesResponse.timestamp
                    print("Timestamp: \(latestTimestamp)")
                    let latestConversionRatesWithUsdPrefixedOnKeys = quotesResponse.quotes
                    
                    var latestConversionRates: [String: Double] = [:]
                    for (usdPrefixedKey, conversionRate) in latestConversionRatesWithUsdPrefixedOnKeys {
                        let newKey = String(usdPrefixedKey.suffix(3))
                        latestConversionRates[newKey] = conversionRate
                    }
                    let quotesResults = QuotesResults(latestConversionRates: latestConversionRates, latestTimestamp: latestTimestamp)
                    completion(quotesResults)
                } catch {
                    print(error.localizedDescription)
                }
            }
        })
        task.resume()
    }
    
    static func getAvailableCurrencies(completion: @escaping(CurrenciesResults) -> Void) {
        
        let parameter = "list"
        let urlString = "\(baseURL)/\(parameter)?access_key=\(apiKey)"
        
        guard let currenciesUrl = URL(string: urlString) else { return }
     
        let urlRequest = URLRequest(url: currenciesUrl)
        let task = URLSession.shared.dataTask(with: urlRequest, completionHandler: { (data, response, error) -> Void in
     
            if let error = error {
                print(error)
                return
            }
     
            // Parse JSON data
            if let data = data {
                do {
                    let currenciesResponse = try JSONDecoder().decode(CurrenciesResponse.self, from: data)
                    
                    let availableCurrencies = currenciesResponse.currencies
                    
                    let currenciesResults = CurrenciesResults(availableCurrencies: availableCurrencies)
                    completion(currenciesResults)
                } catch {
                    print(error.localizedDescription)
                }
            }
        })
        task.resume()
    }
}

