//
//  File.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import Foundation

struct Quotes {
    let timestamp: Int
    let quotes: [Quote]
}

struct Quote {
    let unit: String
    let rate: Double
}

