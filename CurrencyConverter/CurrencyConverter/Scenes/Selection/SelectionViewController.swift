//
//  SelectionViewController.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 02/07/21.
//

import UIKit

final class SelectionViewController: CurrencyConverterViewController {
    
    var delegate: SelectionViewControllerDelegate?
    var filteredUnits = Currency.availableUnits
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
        screen.availableUnitsTableView.delegate = self
        screen.availableUnitsTableView.dataSource = self
        screen.unitSearchBar.delegate = self
        setupUI()
    }
    
    func setupUI() {
        title = Scenes.Selection.title
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
        if searchText == "" {
            filteredUnits = Currency.availableUnits
            return
        }
        filteredUnits = Currency.availableUnits.filter {
            $0.value.lowercased().contains(searchText.lowercased()) || $0.key.lowercased().contains(searchText.lowercased())
        }
        screen.availableUnitsTableView.reloadData()
    }
}

protocol SelectionViewControllerDelegate {
    func updateUnit(at position: Int, to newUnitID: String)
}
