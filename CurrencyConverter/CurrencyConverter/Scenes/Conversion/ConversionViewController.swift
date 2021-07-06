//
//  CurrencyAmountConversionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class ConversionViewController: CurrencyConverterViewController {
    
    // MARK: - Properties
    
    let screen = ConversionScreen()
    
    var globalAmountInDollars: Double = 1.00
    var units = ["USD", "BRL"]
    
    // MARK: - Observed Properties
    
    var conversionRates = Currency.conversionRates {
        didSet {
            DispatchQueue.main.async {
                self.screen.unitsConversionTableView.reloadData()
            }
            print(conversionRates)
        }
    }
        
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
        fetchData()
        setupDelegates()
        setupUI()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    // MARK: - Methods
    
    func fetchData() {
        Provider.getLatestQuotes(completion: { (quotesResults) in
            self.conversionRates = quotesResults.latestConversionRates
            Currency.conversionRates = quotesResults.latestConversionRates
        })
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
        
        guard let conversionRate = conversionRates[unit] else { return cell}
        let amountConverted = globalAmountInDollars * conversionRate
        
        cell.unit = unit
    
        cell.amount = amountConverted
        cell.position = position

//        let unitSymbol = getSymbolForCurrencyCode(code: unit)
//        let newLocale = locale(from: unit)
//        print(unitSymbol)
//        print(newLocale.currencyCode)
//        cell.amountTextField.locale = newLocale
//        print(cell.amountTextField.locale?.currencyCode)
//        print(cell.amountTextField.locale?.currencySymbol)
//        cell.amountTextField.locale = Locale(identifier: "en_US")
        
        cell.delegate = self
        cell.unitButton.backgroundColor = .systemGreen
        cell.unitButton.setTitleColor(.systemBackground, for: .normal)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100
    }
    
    func tableView(_ tableView: UITableView, viewForFooterInSection section: Int) -> UIView? {
        let label = UILabel()
        label.numberOfLines = 0
        label.text = "Last update: 06/06/2021 01:40:24"
        label.textColor = .gray
        label.font = .systemFont(ofSize: 12)
        label.textAlignment = .center
        return label
    }
}

extension ConversionViewController: UnitsConversionTableViewCellDelegate {
    func unitButtonPressed(on cellPosition: Int) {
        let vc = SelectionViewController(for: cellPosition)
        vc.delegate = self
        navigationController?.pushViewController(vc, animated: true)
    }
    
    func amountDidChange(on cellPosition: Int, to amountInDollars: Double) {
        self.globalAmountInDollars = amountInDollars
        let tableViewIndexes = [Int](0..<units.count).map{IndexPath(row: $0, section: 0)}
        let tableViewIndexesToReload = tableViewIndexes.filter {$0.row != cellPosition}
        
        screen.unitsConversionTableView.reloadRows(at: tableViewIndexesToReload, with: .none)
    }
    
}

extension ConversionViewController: SelectionViewControllerDelegate {
    func updateUnit(at position: Int, to newUnitID: String) {
        units[position] = newUnitID
        screen.unitsConversionTableView.reloadData()
    }
}
