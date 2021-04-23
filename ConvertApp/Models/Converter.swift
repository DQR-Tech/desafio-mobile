//
//  Converter.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

enum SelectedConverter{
    case origin
    case destiny
}

struct Converter {
    var origin: String
    var destiny: String
    var valueConverter: Double
    var quotes:[Quote]?
    var selected:SelectedConverter?
    
    func output() -> Double {
        return (valueConverter / findQuoteRate(value: origin)) * findQuoteRate(value: destiny)
    }
    
    func findQuoteRate(value: String) -> Double {
        return quotes?.first{ $0.currency == value}?.rate ?? 0
    }
    
}

