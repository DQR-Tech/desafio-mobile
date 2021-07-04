//
//  CurrencyAmountConversionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class ConversionViewController: CurrencyConverterViewController {
            
    let screen = ConversionScreen()
    
    // MARK: - Life Cycle
    
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
        screen.unitsConversionTableView.dataSource = self
        screen.unitsConversionTableView.delegate = self
    }
}

extension ConversionViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: ConversionScreenUnitsTableViewCell.identifier) as! ConversionScreenUnitsTableViewCell
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
}
