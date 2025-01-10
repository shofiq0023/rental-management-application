import React from 'react';
import { Layout } from 'antd';

const { Content } = Layout;

const MainContent = ({ children }) => {
  return (
    <Content
      style={{
        margin: '24px 16px',
        padding: 20,
        minHeight: 380,
        background: '#f0f2f5',
        borderRadius: 8,
      }}
    >
      {children}
    </Content>
  );
};

export default MainContent;
