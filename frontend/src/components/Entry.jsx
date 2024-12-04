import styled from 'styled-components';
import { Avatar, Col, Image } from 'antd';
import Link from 'antd/es/typography/Link';
import Select from 'react-select'
import React, { useState, useEffect } from 'react';
// import { useCollapse } from 'react-collapsed'
// import { stat } from 'fs';
import moment from 'moment';
import UpdateEntryForm from './UpdateEntryForm';
import DeleteEntryForm from './DeleteEntryButton';

const customStyles = {
  container: (provided) => ({
    ...provided,
    display: 'inline-block',
    // width: '250px',
    minHeight: '1px',
    textAlign: 'left',
    border: 'none',
    // backgroundColor: 'pink',
    paddingLeft: '10px',
  }),
  control: (provided) => ({
    ...provided,
    // border: '2px solid #757575',
    borderRadius: '0',
    minHeight: '1px',
    // height: '42px',
    // backgroundColor: 'pink',
  }),
  input: (provided) => ({
    ...provided,
    minHeight: '1px',
  }),
  dropdownIndicator: (provided) => ({
    ...provided,
    minHeight: '1px',
    paddingTop: '0',
    paddingBottom: '0',
    color: '#757575',
  }),
  indicatorSeparator: (provided) => ({
    ...provided,
    minHeight: '1px',
    // height: '24px',
  }),
  clearIndicator: (provided) => ({
    ...provided,
    minHeight: '1px',
  }),
  valueContainer: (provided) => ({
    ...provided,
    minHeight: '1px',
    // height: '40px',
    paddingTop: '0',
    paddingBottom: '0',
    paddingLeft: '0',
    paddingRight: '0',
    // backgroundColor: 'pink',
  }),
  singleValue: (provided) => ({
    ...provided,
    minHeight: '1px',
    paddingBottom: '2px',
  }),
};

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

function bgcolorChange(props) {
  return 'lightgreen';
}

export default function Entry({ entry, account, statuses, index }) {
//   const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  const handleChange = (selectedOption) => {
    console.log('selected choice: ' + JSON.stringify(selectedOption));
    entry.status.name = selectedOption.value;
    entry['account'] = { id: account.id };
    fetch('/api/entry', {
      method: "POST",
      body: JSON.stringify(entry),
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      credentials: 'include', // Useful for including session ID (and, IIRC, authorization headers)
    })
      .then(response => response.json())
      .then(data => {
      })
      .catch(error => console.error(error));
  }

  // console.log("Story: " + JSON.stringify(story));

  const isDisabled = (process.env.MODE == 'LIVE') ? false : true;

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