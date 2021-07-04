//
//  SelectionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class SelectionViewController: CurrencyConverterViewController {

    let screen = SelectionScreen()
    let unitIndex: Int
    var newUnit: String?
    
    init(for unitIndex: Int) {
        self.unitIndex = unitIndex
    
        super.init()
        super.sceneTitle = "Selection"
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
