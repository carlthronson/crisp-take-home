// import { useCollapse } from 'react-collapsed';
import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import React, { useState, useEffect } from 'react';
// import Link from 'next/link';
import { Link } from 'react-router-dom';
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

const AccountListArea = styled.div`
    padding: 3px;
  transistion: background-color 0.2s ease;
  background-color: #f4f5f7;
    flex-grow: 1;
    min-height: 100px;
`   ;

export default function Account({ account, entries, index, total }) {
//   const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  return (
    <AccountArea>
      {/* <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        gap: 8
      }}> */}
      <span href='' style={{  }}>Account Name: {account.name}</span>
      <span href='' style={{  }}>Account ID: {account.label}</span>
      {/* </div> */}
      <div>
      <UpdateAccountForm account={account}></UpdateAccountForm>
      <DeleteAccountForm account={account}></DeleteAccountForm>
      <CreateEntryForm account={account}></CreateEntryForm>
      </div>
      {/* <span href='' style={{  }}>{index + 1}/{total} ({entries.filter(x => x.account === account.name).length}) {account.name}</span> */}
      {/* <Link href='' style={{  }} {...getToggleProps()}>{isExpanded ? 'Collapse' : 'Expand'}</Link> */}
      {/* <section {...getCollapseProps()}> */}
        {entries.filter(x => x.account === account.name).map((item, index) => (
          <Entry key={index} entry={item} account={account} index={index} />
        ))}
      {/* </section> */}
    </AccountArea>
  );
}