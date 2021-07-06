//
//  CurrenciesResponse.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import Foundation

// MARK: - CurrenciesResponse

struct CurrenciesResponse: Codable {
    let success: Bool
    let terms, privacy: String
    let currencies: [String: String]
}

