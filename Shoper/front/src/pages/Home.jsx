import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { SITE_CONFIG } from '../config/site';
import { media } from '../styles/MediaQueries';
import { productService } from '../api/products';
import { GridContainer, Section } from '../styles/common/Container';
import { Title } from '../styles/common/Typography';
import { Card } from '../styles/common/Card';

const Home = () => {
  const [popularProducts, setPopularProdeucts] = useState([]);
  const [newProducts, setNewProducts] = useState([]);

  useEffect(() => {
    const loadProducts = async () => {
      try {
        const products = await productService.getProducts();
        console.log(products);
        setNewProducts(products.filter((p) => p.isNew));
        setPopularProdeucts(products.filter((p) => p.isPopular));
      } catch (error) {
        console.error(error);
      }
    };

    loadProducts();
  }, []);

  return (
    <>
      <Banner>
        <div>
          <BannerTitle>{SITE_CONFIG.name}</BannerTitle>
          <BannerSubTitle>{SITE_CONFIG.description}</BannerSubTitle>
        </div>
      </Banner>

      <Section>
        <Title>인기 상품</Title>
        <GridContainer>
          {popularProducts.map((product) => (
            <Card>
              <img src="" alt="" />
              <div>
                <h3>{product.name}</h3>
                <span>{product.price}원</span>
              </div>
            </Card>
          ))}
        </GridContainer>
      </Section>

      <Section>
        <Title>신상품</Title>
        <GridContainer></GridContainer>
      </Section>
    </>
  );
};

const Banner = styled.div`
  background: linear-gradient(300deg, ${({ theme }) => theme.colors.primary}, ${({ theme }) => theme.colors.info});
  padding: ${({ theme }) => theme.spacing[16]} 0;
  color: ${({ theme }) => theme.colors.white};
`;

const BannerTitle = styled.h1`
  font-size: ${({ theme }) => theme.fontSizes.xl};
  font-weight: ${({ theme }) => theme.fontWeights.bold};
  margin-bottom: ${({ theme }) => theme.spacing[4]};

  ${media.md`
    font-size: ${({ theme }) => theme.fontSizes['4xl']};
  `}
`;

const BannerSubTitle = styled.p`
  font-size: ${({ theme }) => theme.fontSizes.base};

  ${media.md`
    font-size: ${({ theme }) => theme.fontSizes.xl};
  `}
`;

export default Home;
