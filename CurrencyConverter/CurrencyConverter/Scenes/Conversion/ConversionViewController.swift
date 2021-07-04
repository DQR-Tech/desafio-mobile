//
//  CurrencyAmountConversionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class ConversionViewController: CurrencyConverterViewController {
        
    let screen = ConversionScreen()
    
    override init() {
        super.init()
        sceneTitle = "Conversion"
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = screen
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupDelegates()
        setupUI()
    }
        
    func setupUI() {
        title = Scenes.Conversion.title
    }
    
    func setupDelegates() {
        screen.delegate = self
    }
}

extension ConversionViewController: ConversionScreenDelegate {
    func unitSelectionButtonPressed(for buttonPosition: UnitSelectionButtonPosition) {
        print("\(buttonPosition.rawValue) Unit Selection Button Pressed")
        let vc = SelectionViewController(for: buttonPosition)
        navigationController?.pushViewController(vc, animated: true)
    }
}
