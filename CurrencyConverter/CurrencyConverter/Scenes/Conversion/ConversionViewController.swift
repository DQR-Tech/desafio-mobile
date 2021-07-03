//
//  CurrencyAmountConversionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class ConversionViewController: UIViewController {
    
    let screen = ConversionScreen()
    var originUnit = CurrencyUnit(name: "Dólar", id: "USD", rate: 1)
    var targetUnit = CurrencyUnit(name: "Real", id: "BRL", rate: 5.04)
    
    override func loadView() {
        self.view = screen

        screen.delegate = self
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        title = Scenes.Conversion.title
    }
}

extension ConversionViewController: ConversionScreenDelegate {
    func buttonPressed() {
        let viewController = SelectionViewController()
        navigationController?.pushViewController(viewController, animated: true)
    }
}
