//
//  Quotes.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation

struct QuotesResponse: Codable {
    let success: Bool
    let privacy: String?
    let terms: String?
    let quotes: [String:Float]?
    
    init(sucess: Bool, privacy:String, terms: String, quotes: [String:Float] ) {
        self.success = sucess
        self.privacy = privacy
        self.terms = terms
        self.quotes = quotes
    }
    
}

struct Quote: Codable, Equatable {
    var currency: String
    var rate: Double
    
    static func quotes(from dict: [String: Float]) -> [Quote] {
        return dict.map { Quote(currency: String($0.key.suffix(3)), rate: Double($0.value)) }
    }
}

