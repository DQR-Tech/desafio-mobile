//
//  CurrencyConverterViewControllerDebugging.swift
//  CurrencyConverterDebugging
//
//  Created by Lucas Werner Kuipers on 04/07/21.
//


extension CurrencyConverterViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("🎬\(sceneTitle) Scene did load")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        print("✨\(sceneTitle) Scene will appear")
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        print("👋\(sceneTitle) Scene did appear")
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        print("👻\(sceneTitle) Scene will disappear")
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        print("🌑\(sceneTitle) Scene did disappear")
    }
}
