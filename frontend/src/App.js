import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Accounts from './components/Accounts';
import AccountEntries from './components/AccountEntries';
import Navigation from './components/Navigation';

function App() {
  return (
    <Router>
      <div className="App">
        <Accounts></Accounts>
        {/* <Navigation />
        <Switch>
          <Route path="/accounts" component={Accounts} />
          <Route path="/entries" component={AccountEntries} />
          <Route path="/" exact component={() => <h1>Welcome to Financial App</h1>} />
        </Switch> */}
      </div>
    </Router>
  );
}

export default App;
