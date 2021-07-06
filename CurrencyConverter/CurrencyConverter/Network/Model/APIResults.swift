//
//  Currencies.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import Foundation

public struct APIResults {
    var latestConversionRates: [String : Double]
    var availableCurrencies: [String: String]
    var latestTimestamp: Int
}

public struct QuotesResults {
    var latestConversionRates: [String : Double]
    var latestTimestamp: Int
}

public struct CurrenciesResults {
    var availableCurrencies: [String: String]
}
