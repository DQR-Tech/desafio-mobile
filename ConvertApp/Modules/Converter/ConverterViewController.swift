//
//  ViewController.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import UIKit

class ConverterViewController: UIViewController {

    @IBOutlet weak var buttonOrigin: UIButton!
    @IBOutlet weak var buttonDestiny: UIButton!
    @IBOutlet weak var labelResult: UILabel!
    @IBOutlet weak var txtFieldValue: CurrencyField!
    
    var onErrorHandling : ((String) -> Void)?
    
    var converterViewModel = ConverterViewModel()

    override func viewDidLoad() {
        super.viewDidLoad()
        load()
    }
    
    private func load(){
        txtFieldValue.removeSymbol = true
        txtFieldValue.locale = Locale(identifier:"pt-BR")
        
        showLoading()
        converterViewModel.getLive{error in
            DispatchQueue.main.async {
                self.dismissLoading()
                if let error = error{
                        self.showError(error: error)
                }
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        setTitleButtons()
    }
    
    func setTitleButtons(){
        if(converterViewModel.converter.destiny != ""){
            buttonDestiny.setTitle(converterViewModel.converter.destiny, for: .normal)
        }
        if(converterViewModel.converter.origin != ""){
            buttonOrigin.setTitle(converterViewModel.converter.origin, for: .normal)
        }
    }

    @IBAction func convertPressed(_ sender: Any) {
        
        if(converterViewModel.validate()){
            let result = converterViewModel.toConvert()
            labelResult.text = result
        }else{
            self.showError(error: converterViewModel.errorResponse)

        }
    }
    @IBAction func destinyPressed(_ sender: Any) {
        converterViewModel.converterSelected(selected: .destiny)
        performSegue(withIdentifier: converterViewModel.segueIdentifierCurrencies, sender: self)
    }
    
    @IBAction func originPressed(_ sender: Any) {
        converterViewModel.converterSelected(selected: .origin)
        performSegue(withIdentifier: converterViewModel.segueIdentifierCurrencies, sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == converterViewModel.segueIdentifierCurrencies {
            if let destinationViewController = segue.destination as? CurrenciesViewController {
                destinationViewController.currenciesViewModel.callBackConverter = { [weak self] converter in
                    self?.converterViewModel.converter = converter
                }
                destinationViewController.currenciesViewModel.converter = converterViewModel.converter
            }
        }
    }
    
    @IBAction func edtingChangedInput(_ sender: Any) {
        self.converterViewModel.converter.valueConverter = Double(truncating: txtFieldValue.decimal as NSNumber)
    }
    
}

