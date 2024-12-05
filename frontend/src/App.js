import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Accounts from './components/Accounts';

function App() {
  return (
    <Router>
      <div className="App">
        <Accounts></Accounts>
      </div>
    </Router>
  );
}

export default App;
