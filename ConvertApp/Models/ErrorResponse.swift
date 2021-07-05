//
//  ErrorResponse.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation


enum ErrorTypes: String{
    case errorAPI = "Erro com conexão API"
    case errorApp = "Erro com app"
}

struct ErrorResponse {
    var type: ErrorTypes
    var message: String
}

enum MessageValidationError: String {
    case emptyOrigin = "Por favor selecione moeda de origem"
    case emptyDestiny = "Por favor selecione moeda de destino"
    case emptyTxtConverter = "Campo vazio"

}

