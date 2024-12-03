import React, { useState } from 'react';
import { useQuery, useMutation, gql } from '@apollo/client';

const GET_ENTRIES = gql`
  query GetEntries {
    entries {
      id
      name
      account
      amount
      category
    }
  }
`;

const CREATE_ENTRY = gql`
  mutation CreateEntry($name: String!, $account: String!, $amount: BigDecimal!, $category: String!) {
    createEntry(name: $name, account: $account, amount: $amount, category: $category) {
      id
      name
      account
      amount
      category
    }
  }
`;

const UPDATE_ENTRY = gql`
  mutation UpdateEntry($id: ID!, $name: String!, $account: String!, $amount: BigDecimal!, $category: String!) {
    updateEntry(id: $id, name: $name, account: $account, amount: $amount, category: $category) {
      id
      name
      account
      amount
      category
    }
  }
`;

const DELETE_ENTRY = gql`
  mutation DeleteEntry($id: ID!) {
    deleteEntry(id: $id) {
      id
    }
  }
`;

function AccountEntries() {
  const [newEntry, setNewEntry] = useState({ name: '', account: '', amount: 0, category: '' });
  const { loading, error, data } = useQuery(GET_ENTRIES);
  const [createEntry] = useMutation(CREATE_ENTRY, {
    refetchQueries: [{ query: GET_ENTRIES }],
  });
  const [updateEntry] = useMutation(UPDATE_ENTRY);
  const [deleteEntry] = useMutation(DELETE_ENTRY, {
    refetchQueries: [{ query: GET_ENTRIES }],
  });

  const handleCreateEntry = async () => {
    await createEntry({ variables: newEntry });
    setNewEntry({ name: '', account: '', amount: 0, category: '' });
  };

  const handleUpdateEntry = async (id, name, account, amount, category) => {
    await updateEntry({ variables: { id, name, account, amount, category } });
  };

  const handleDeleteEntry = async (id) => {
    await deleteEntry({ variables: { id } });
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h2>Account Entries</h2>
      <input
        value={newEntry.name}
        onChange={(e) => setNewEntry({ ...newEntry, name: e.target.value })}
        placeholder="Entry Name"
      />
      <input
        value={newEntry.account}
        onChange={(e) => setNewEntry({ ...newEntry, account: e.target.value })}
        placeholder="Account Name"
      />
      <input
        type="number"
        value={newEntry.amount}
        onChange={(e) => setNewEntry({ ...newEntry, amount: parseFloat(e.target.value) })}
        placeholder="Amount"
      />
      <input
        value={newEntry.category}
        onChange={(e) => setNewEntry({ ...newEntry, category: e.target.value })}
        placeholder="Category"
      />
      <button onClick={handleCreateEntry}>Create Entry</button>

      {data.entries.map(entry => (
        <div key={entry.id}>
          <span>{entry.name} - ${entry.amount} - {entry.category}</span>
          <button onClick={() => handleUpdateEntry(entry.id, entry.name, entry.account, entry.amount + 10, entry.category)}>Update</button>
          <button onClick={() => handleDeleteEntry(entry.id)}>Delete</button>
        </div>
      ))}
    </div>
  );
}

export default AccountEntries;