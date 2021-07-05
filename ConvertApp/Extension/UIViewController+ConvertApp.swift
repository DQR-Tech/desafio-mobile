//
//  UIViewController+ConvertApp.swift
//  ConvertApp
//
//  Created by Guilherme Prata Costa on 22/04/21.
//

import Foundation
import UIKit

extension UIViewController{
    func showError(error:ErrorResponse){
        
        let alert = UIAlertController(title: error.type.rawValue, message: error.message, preferredStyle: UIAlertController.Style.alert)

        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))

        self.present(alert, animated: true, completion: nil)
    }
    
    func showLoading(){
        let alert = UIAlertController(title: nil, message: "Carregando", preferredStyle: .alert)

        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.style = UIActivityIndicatorView.Style.medium
        loadingIndicator.startAnimating();

        alert.view.addSubview(loadingIndicator)
        present(alert, animated: true, completion: nil)
    }
    
    func dismissLoading() {
        if let vc = self.presentedViewController, vc is UIAlertController {
            dismiss(animated: false, completion: nil)
        }
    }
}
