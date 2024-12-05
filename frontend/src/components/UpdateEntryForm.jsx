import React, { useState } from 'react';
import Popup from 'reactjs-popup';
import 'reactjs-popup/dist/index.css';
import { useMutation, gql } from '@apollo/client';

const UPDATE_ENTRY = gql`
  mutation UpdateEntry($id: ID, $name: String!, $amount: BigDecimal!, $category: String!, $account: String!) {
    updateEntry(id: $id, name: $name, amount: $amount, category: $category, account: $account) {
      id
      name
      amount
      category
      account
    }
  }
`;

const UpdateEntryForm = ({entry}) => {
    const [updateEntry] = useMutation(UPDATE_ENTRY, {
      });
    
    const [formData, setFormData] = useState({
    id: entry.id,
    name: entry.name,
    amount: entry.amount,
    category: entry.category,
    account: entry.account
  });

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    console.log(JSON.stringify(formData));
    await updateEntry({ variables: formData });
  };

  return (
    <Popup trigger={<button>Update Entry</button>} modal>
      {close => (
        <div className="modal">
          <button className="close" onClick={close}>
            &times;
          </button>
          <h2>Update entry {entry.name}</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleInputChange}
              placeholder="Name"
            />
            <input
              type="text"
              name="amount"
              value={formData.amount}
              onChange={handleInputChange}
              placeholder="Amount"
            />
            <input
              type="text"
              name="category"
              value={formData.category}
              onChange={handleInputChange}
              placeholder="Category"
            />
            <button type="submit">Submit</button>
          </form>
        </div>
      )}
    </Popup>
  );
};

export default UpdateEntryForm;