//
//  CurrencyAmountConversionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class ConversionViewController: CurrencyConverterViewController {
                    
    var globalAmountInDollars: Double = 1.00
    let screen = ConversionScreen()
    let units = [
        "USD",
        "BRL"
    ]
    
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
        return units.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: UnitsConversionTableViewCell.identifier) as! UnitsConversionTableViewCell
        let position = indexPath.row
        
        let unit = units[position]
        
        guard let conversionRate = Currency.conversionRates[unit] else { return cell}
        let amountConverted = globalAmountInDollars * conversionRate
        
        cell.unit = unit
    
        cell.amount = amountConverted
        cell.position = position
        
        cell.delegate = self
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
    }
}

extension ConversionViewController: UnitsConversionTableViewCellDelegate {
    func amountDidChange(on cellPosition: Int, to amountInDollars: Double) {
        print("VIEW CONTROLLER: cellPosition: \(cellPosition) and amount in dollars: \(amountInDollars)")
        self.globalAmountInDollars = amountInDollars
        
        let tableViewIndexes = [Int](0..<units.count).map{IndexPath(row: $0, section: 0)}
        let tableViewIndexesToReload = tableViewIndexes.filter {$0.row != cellPosition}
        print(tableViewIndexesToReload)
        
        screen.unitsConversionTableView.reloadRows(at: tableViewIndexesToReload, with: .none)
    }
}
