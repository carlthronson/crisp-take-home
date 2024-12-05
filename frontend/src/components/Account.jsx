import styled from 'styled-components';
import React from 'react';
import Entry from './Entry';
import UpdateAccountForm from './UpdateAccountForm';
import DeleteAccountForm from './DeleteAccountButton';
import CreateEntryForm from './CreateEntryForm';

const AccountArea = styled.div`
    border-radius: 10px;
  box-shadow: 5px 5px 5px 2px grey;
    padding: 8px;
    color: #000;
    margin-bottom: 8px;
    min-height: 90px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: #DCDCDC;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

export default function Account({ account, entries }) {

  return (
    <AccountArea>
      <span href='' style={{  }}>Account Name: {account.name}</span>
      <span href='' style={{  }}>Account ID: {account.label}</span>
      <div>
      <UpdateAccountForm account={account}></UpdateAccountForm>
      <DeleteAccountForm account={account}></DeleteAccountForm>
      <CreateEntryForm account={account}></CreateEntryForm>
      </div>
        {entries.filter(x => x.account === account.name).map((item, index) => (
          <Entry key={index} entry={item} account={account} index={index} />
        ))}
    </AccountArea>
  );
}