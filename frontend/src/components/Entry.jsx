import styled from 'styled-components';
import React from 'react';
import UpdateEntryForm from './UpdateEntryForm';
import DeleteEntryForm from './DeleteEntryButton';

const EntryArea = styled.div`
    border-radius: 2px;
  box-shadow: 1px 1px 1px 1px grey;
    padding: 8px;
    color: black;
    margin-top: 8px;
    // margin-bottom: 4px;
    min-height: 25px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: cyan;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

const Row = styled.div`
  flex: 1;
  // background-color: pink;
  padding: 3px
`;

export default function Entry({ entry, account, statuses, index }) {

  return (
    <EntryArea>
        <Row>Name: {entry.name}</Row>
        <Row>Amount: {entry.amount}</Row>
        <Row>Category: {entry.category}</Row>
        <div>
      <UpdateEntryForm entry={entry}></UpdateEntryForm>
      <DeleteEntryForm entry={entry}></DeleteEntryForm>
      </div>
    </EntryArea>
  );
}