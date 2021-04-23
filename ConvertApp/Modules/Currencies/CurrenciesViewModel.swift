//
//  CurrenciesViewModel.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation

class CurrenciesViewModel {
    
    // MARK: - Initialization
    init(model: CurrenciesResponse? = nil) {
        if let inputModel = model {
            currencies = Exchange.currencies(from: inputModel.currencies ?? [:] )
        }
    }
    
    let cellIdentifier = "cellCurrency"
    var callBackConverter: ((_ converter: Converter )-> Void)?
    
    var converter: Converter = Converter(origin: "", destiny: "", valueConverter: 0, selected: nil)
    
    var currencies = [Exchange]()
    var currenciesFiltered = [Exchange]()

}

extension CurrenciesViewModel {
    func getList(completion:  @escaping (ErrorResponse?) -> Void) {
        NetworkManager.shared.getList(completion: { (response, error) in
            if(error == nil){
                guard let r = response else { return }
                self.currencies = Exchange.currencies(from: r.currencies ?? [:] )
                self.currenciesFiltered = self.currencies
                completion(nil)
            }else{
                completion(error)
            }
        })
    }
    
    func tapped(index:IndexPath){
        if(converter.selected == SelectedConverter.origin){
            converter.origin = currenciesFiltered[index.row].code
        }else{
            converter.destiny = currenciesFiltered[index.row].code
        }
        callBackConverter?(converter)
    }
    
    func filtered(_ searchText: String){
        if searchText != "" {
            currenciesFiltered = currencies.filter { item in
                return item.code.lowercased().contains(searchText.lowercased()) || item.contry.lowercased().contains(searchText.lowercased())
            }
            
        } else {
            currenciesFiltered = currencies
        }
    }
}
