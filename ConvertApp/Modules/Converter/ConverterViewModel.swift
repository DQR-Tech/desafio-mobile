//
//  ConverterViewModel.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

class ConverterViewModel {
    
    // MARK: - Initialization
    init(model: Converter? = nil) {
        if let inputModel = model {
            converter = inputModel
        }
    }
    
    var converter: Converter = Converter(origin: "", destiny: "", valueConverter: 0,quotes:nil, selected: nil)
    var errorResponse:ErrorResponse = ErrorResponse(type: .errorApp , message: "")
    
    let segueIdentifierCurrencies:String = "segueCurrencies"
    
}

extension ConverterViewModel {

    func getLive (completion: @escaping (ErrorResponse?) -> Void) {
        NetworkManager.shared.getLive(completion: { (response, error) in
            if let errorApi = error {
                completion(errorApi)
            }else{
                guard let r = response else { return }
                self.converter.quotes = Quote.quotes(from: r.quotes ?? [:] )
                completion(nil)
            }
        })
    }
    
    func converterSelected(selected: SelectedConverter) -> Void{
        self.converter.selected = selected
    }
    
    func toConvert() -> String{
        return String(format: "%.02f",self.converter.output())
    }
    
    
    func validate() -> Bool{
    
        if(converter.origin == ""){
            errorResponse.message = MessageValidationError.emptyOrigin.rawValue
            return false
        }
        
        if(converter.destiny == ""){
            errorResponse.message = MessageValidationError.emptyDestiny.rawValue
            return false
        }
                
        if(converter.valueConverter == 0){
            errorResponse.message = MessageValidationError.emptyTxtConverter.rawValue
            return false
        }
        
        return true
    }
    
}
