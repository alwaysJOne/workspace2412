import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { SITE_CONFIG } from '../config/site';
import { media } from '../styles/MediaQueries';
import { productService } from '../api/products';
import { GridContainer, Section } from '../styles/common/Container';
import { Price, Title } from '../styles/common/Typography';
import { Card, CardContent, CardImage, CardTitle } from '../styles/common/Card';
import { toast } from 'react-toastify';
import { ClipLoader } from 'react-spinners';

const Home = () => {
  const [popularProducts, setPopularProdeucts] = useState([]);
  const [newProducts, setNewProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadProducts = async () => {
      try {
        setLoading(true);
        const products = await productService.getProducts();
        console.log(products);
        setNewProducts(products.filter((p) => p.isNew));
        setPopularProdeucts(products.filter((p) => p.isPopular));
      } catch (error) {
        console.error(error);
        const errorMessage = '상품을 불러오는데 실패했습니다.';
        setError(errorMessage);
        toast.error(errorMessage);
      } finally {
        setLoading(false);
      }
    };

    loadProducts();
  }, []);

  if (loading) {
    return (
      <LoadingContainer>
        <ThemedLodader size={50} aria-label="Loading Spinner" />
      </LoadingContainer>
    );
  }

  if (error) {
    return null;
  }

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
              <CardImage src={product.image} />
              <CardContent>
                <CardTitle>{product.name}</CardTitle>
                <Price>{Number(product.price).toLocaleString()}원</Price>
              </CardContent>
            </Card>
          ))}
        </GridContainer>
      </Section>

      <Section>
        <Title>신상품</Title>
        <GridContainer>
          {newProducts.map((product) => (
            <Card>
              <CardImage src={product.image} />
              <CardContent>
                <CardTitle>{product.name}</CardTitle>
                <Price>{Number(product.price).toLocaleString()}원</Price>
              </CardContent>
            </Card>
          ))}
        </GridContainer>
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

const LoadingContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
`;
const ThemedLodader = styled(ClipLoader)`
  color: ${({ theme }) => theme.colors.primary};
`;
export default Home;
