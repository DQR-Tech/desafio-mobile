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
    
    var newUnit: String? = nil
    var delegate: SelectionViewControllerDelegate? = nil
    
    var filteredUnits = Currency.availableUnits {
        didSet {
            DispatchQueue.main.async {
                self.screen.availableUnitsTableView.reloadData()
            }
        }
    }
    
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
        fetchData()
        setupDelegates()
        setupUI()
    }
    
    func fetchData() {
        Provider.getAvailableCurrencies(completion: { currenciesResults in
            Currency.availableUnits = currenciesResults.availableCurrencies
            self.filteredUnits = Currency.availableUnits
        })
    }
    
    func setupDelegates() {
        screen.availableUnitsTableView.delegate = self
        screen.availableUnitsTableView.dataSource = self
        screen.unitSearchBar.delegate = self
    }
    
    func setupUI() {
        title = Scenes.Selection.title
        self.sceneTitle = "Selection"
    }
}

extension SelectionViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return filteredUnits.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: AvailableUnitsTableViewCell.identifier, for: indexPath) as! AvailableUnitsTableViewCell
        cell.unitId.text = Array(filteredUnits.keys)[indexPath.row]
        cell.unitName.text = Array(filteredUnits.values)[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let position = unitIndex
        let unitID = Array(filteredUnits.keys)[indexPath.row]
        
        self.delegate?.updateUnit(at: position, to: unitID)
        navigationController?.popViewController(animated: true)
    }
}

extension SelectionViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        
        let text = searchText.replacingOccurrences(of: " ", with: "").lowercased().letters
        
        if text == "" {
            filteredUnits = Currency.availableUnits
            return
        }
        filteredUnits = Currency.availableUnits.filter {
            
            let unitId = $0.key.lowercased()
            let unitName = $0.value.lowercased()
            
            return unitId.contains(text) || unitName.contains(text)
        }
        screen.availableUnitsTableView.reloadData()
    }
}

protocol SelectionViewControllerDelegate {
    func updateUnit(at position: Int, to newUnitID: String)
}
