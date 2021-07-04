//
//  SelectionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class SelectionViewController: CurrencyConverterViewController {

    let screen = SelectionScreen()
    var unitSelectionButtonPosition: UnitSelectionButtonPosition? = nil
    
    init(for unitSelectionButtonPosition: UnitSelectionButtonPosition) {
        super.init()
        self.unitSelectionButtonPosition = unitSelectionButtonPosition
        sceneTitle = "Selection"
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        self.view = screen
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.sceneTitle = "Selection"
        setupUI()
    }
    
    func setupUI() {
        title = Scenes.Selection.title
    }
}
