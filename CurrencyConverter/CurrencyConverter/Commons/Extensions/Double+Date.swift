//
//  Doubel+Date.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 06/07/21.
//

import Foundation

extension Double {
    func getDateStringFromUTC() -> String {
        let date = Date(timeIntervalSince1970: self)

        let dateFormatter = DateFormatter()
        dateFormatter.locale = Locale(identifier: "en_US")
        dateFormatter.dateStyle = .full
        dateFormatter.timeStyle = .full

        return dateFormatter.string(from: date)
    }
}
