//
//  AvailableUnitsTableViewCell.swift
//  CurrencyConverter
//
//  Created by Lucas Werner Kuipers on 05/07/21.
//

import UIKit

class AvailableUnitsTableViewCell: UITableViewCell {
    
    lazy var unitName: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Dollar"
        return label
    }()
    
    lazy var unitId: UILabel = {
        let label = UILabel(frame: .zero)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "USD"
        return label
    }()
    
    static let identifier = "AvailableUnitsTableViewCell"
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupView()
        setupSubviews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension AvailableUnitsTableViewCell: ParentCodeView {
    func addSubviews() {
        addSubview(unitName)
        addSubview(unitId)
    }
    
    func setupConstraints() {
        unitName.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        unitName.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20).isActive = true
        
        unitId.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
        unitId.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20).isActive = true
    }
    
    func setupView() {
        
    }
}
