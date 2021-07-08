//
//  CurrencyListModel.swift
//  DesafioBTG
//
//  Created by Euclides Medeiros on 07/07/21.
//

import Foundation

// MARK: - currencyListModel
struct CurrencyListModel: Codable {
    let success: Bool
    let terms, privacy: String
    let currencies: [String: String]
}
