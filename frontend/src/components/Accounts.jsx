'use client'
import styled from 'styled-components';
import Overview from './Overview';
import { useQuery, gql } from '@apollo/client';

const GET_DATA = gql`
  query GetData {
    accountTypes
    accounts {
      id
      name
      label
      accountTypeName
    }
    entries {
      id
      name
      amount
      account
      category
    } 
  }
`;

const Title = styled.h1`
    text-align: center;
`   ;

export default function Accounts() {
  const { loading, error, data } = useQuery(GET_DATA);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return <div>
    <Title>{data.entries.length} account entries...</Title>
    <div>
      <Overview accountTypes={data.accountTypes} accounts={data.accounts} entries={data.entries}></Overview>
    </div>
  </div>
}