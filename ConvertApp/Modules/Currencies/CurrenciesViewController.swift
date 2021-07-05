//
//  CurrencyViewController.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 21/04/21.
//

import Foundation
import UIKit

class CurrenciesViewController: UIViewController {

    @IBOutlet var tableView: UITableView!
    
    var currenciesViewModel = CurrenciesViewModel()
    
    lazy var searchController = UISearchController(searchResultsController: nil)

                    
    override func viewDidLoad() {
        super.viewDidLoad()
        load()
    }
    
    private func load(){
        tableView.delegate = self
        tableView.dataSource = self
        configureSearch()
        
        currenciesViewModel.getList { error in
            if let error = error{
                self.showError(error: error)
            }else{
                DispatchQueue.main.async {
                    self.updateUI()
                }
            }
        }
        
    }
    
    //MARK: Functions
    private func configureSearch(){
        searchController.searchResultsUpdater = self
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Pesquisar moeda..."
        searchController.searchBar.tintColor = .black
        navigationItem.hidesSearchBarWhenScrolling = false
        navigationItem.searchController = searchController
        definesPresentationContext = true
    }
    
    func updateUI() -> Void{
        self.tableView.reloadData()
    }
    
}

extension CurrenciesViewController: UITableViewDelegate{
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        currenciesViewModel.tapped(index: indexPath)
        self.navigationController?.popViewController(animated: true)

    }
}

extension CurrenciesViewController: UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return currenciesViewModel.currenciesFiltered.count
    }

    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: currenciesViewModel.cellIdentifier, for: indexPath)
                
        let exchange:Exchange = currenciesViewModel.currenciesFiltered[indexPath.row]
        cell.textLabel?.text = " (\(exchange.code)) - \(exchange.contry)"
        
        return cell
    }
}

extension CurrenciesViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        if let text = searchController.searchBar.text {
            currenciesViewModel.filtered(text)
            updateUI()
        }
    }
}
