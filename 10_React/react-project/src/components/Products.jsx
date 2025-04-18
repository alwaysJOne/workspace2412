import React from 'react'
import styled from 'styled-components'

const productItems = [{
    product_name: "삼성 TV",
    price: 10000,
    color: "블랙"
},{
    product_name: "엘지 냉장고",
    price: 30000,
    color: "베이지"
},{
    product_name: "애플 노트북",
    price: 50000,
    color: "그레이"
}]

const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
`

const Th = styled.th`
    background: #423c3c;
    color: white;
    padding: 12px;
    border: 1px solid #dddddd;
`
const Td = styled.td`
    padding: 12px;
    border: 1px solid #dddddd;
`

const Tr = styled.tr`
    &:hover{
        background: #f1f1f1;
    }
`

const Products = () => {
  return (
    <div>
        <Table>
            <thead>
                <tr>
                    <Th>제품명</Th> 
                    <Th>가격</Th>
                    <Th>색상</Th>
                </tr>
            </thead>
            <tbody>
                {productItems.map((p) => 
                        <Tr>
                            <Td>{p.product_name}</Td> 
                            <Td>{p.price}</Td>
                            <Td>{p.color}</Td>
                        </Tr>
                    )
                }
            </tbody>
        </Table>
    </div>
  )
}

export default Products