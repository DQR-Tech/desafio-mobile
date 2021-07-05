//
//  Currencies.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation


struct CurrenciesResponse: Codable {
    let success: Bool
    let privacy: String?
    let terms: String?
    let currencies: [String:String]?
    
    private enum CodingKeys : String, CodingKey {
        case success = "success"
        case privacy = "privacy"
        case terms = "terms"
        case currencies = "currencies"
    }
    
    init(sucess: Bool, privacy:String, terms: String, currencies: [String:String] ) {
        self.success = sucess
        self.privacy = privacy
        self.terms = terms
        self.currencies = currencies
    }
    
}

struct Exchange{
    let code:String
    let contry: String
    
    static func currencies(from dict: [String: String]) -> [Exchange] {
        return dict.sorted(by: <).map { Exchange(code: $0.key, contry: $0.value) }
    }
}
