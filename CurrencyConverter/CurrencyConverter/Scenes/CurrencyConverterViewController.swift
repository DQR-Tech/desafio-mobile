//
//  CurrencyConverterViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//

import UIKit

class CurrencyConverterViewController: UIViewController {
    
    var sceneTitle: String = ""
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    init(title: String) {
        self.sceneTitle = title
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
