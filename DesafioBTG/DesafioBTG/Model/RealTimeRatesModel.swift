//
//  RealTimeRatesModel.swift
//  DesafioBTG
//
//  Created by Euclides Medeiros on 07/07/21.
//

import Foundation

// MARK: - RealTimeRatesModel
struct RealTimeRatesModel: Codable {
    let success: Bool
    let terms, privacy: String
    let timestamp: Int
    let source: String
    let quotes: [String: Double]
}
