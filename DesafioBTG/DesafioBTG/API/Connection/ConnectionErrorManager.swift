//
//  ConnectionErrorManager.swift
//  DesafioBTG
//
//  Created by Euclides Medeiros on 07/07/21.
//

import Foundation
import UIKit

class ConnectionErrorManager {
    static func isSuccessfulStatusCode(statusCode: Int) -> Bool {
        if (statusCode >= 200 && statusCode < 300) {
            return true
        }
    return false
    }
}
