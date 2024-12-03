import React from 'react';
import { Link } from 'react-router-dom';

function Navigation() {
  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/accounts">Accounts</Link></li>
        <li><Link to="/entries">Account Entries</Link></li>
      </ul>
    </nav>
  );
}

export default Navigation;
