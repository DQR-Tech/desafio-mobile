//
//  SelectionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

class SelectionViewController: UIViewController {

    let screen = SelectionScreen()
    
    override func loadView() {
        self.view = screen
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = Scenes.Selection.title
    }
}
