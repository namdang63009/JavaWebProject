USE [ShoeStoreDB]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](30) NULL,
	[roles] [int] NULL,
 CONSTRAINT [PK_tbl_account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_customer]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_customer](
	[custID] [varchar](20) NOT NULL,
	[lastName] [varchar](15) NULL,
	[middleName] [varchar](30) NULL,
	[firstName] [varchar](15) NULL,
	[address] [varchar](250) NULL,
	[phone] [varchar](11) NULL,
	[cust_Level] [int] NULL,
 CONSTRAINT [PK_tbl_customer] PRIMARY KEY CLUSTERED 
(
	[custID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_order]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_order](
	[orderID] [varchar](10) NOT NULL,
	[orderDate] [datetime] NULL,
	[custID] [varchar](20) NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_tbl_order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_orderDetail]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_orderDetail](
	[id] [int] NOT NULL,
	[productID] [varchar](10) NULL,
	[quantity] [int] NULL,
	[unitPrice] [float] NULL,
	[total] [float] NULL,
	[orderID] [varchar](10) NULL,
 CONSTRAINT [PK_tbl_orderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_shoeBySize]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_shoeBySize](
	[shoeID] [varchar](10) NULL,
	[sizeID] [varchar](3) NULL,
	[quantityShoeBySize] [int] NULL,
	[price] [float] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_shoes]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_shoes](
	[shoesID] [varchar](10) NOT NULL,
	[description] [varchar](50) NULL,
	[quantityOfShoe] [int] NULL,
	[disable] [bit] NULL,
 CONSTRAINT [PK_tbl_shoes] PRIMARY KEY CLUSTERED 
(
	[shoesID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_sizes]    Script Date: 11/3/2018 2:11:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_sizes](
	[id] [varchar](3) NOT NULL,
	[sizes] [int] NULL,
	[country] [varchar](50) NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_tbl_sizes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[tbl_account]  WITH CHECK ADD  CONSTRAINT [FK_tbl_account_tbl_customer] FOREIGN KEY([username])
REFERENCES [dbo].[tbl_customer] ([custID])
GO
ALTER TABLE [dbo].[tbl_account] CHECK CONSTRAINT [FK_tbl_account_tbl_customer]
GO
ALTER TABLE [dbo].[tbl_order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_order_tbl_customer] FOREIGN KEY([custID])
REFERENCES [dbo].[tbl_customer] ([custID])
GO
ALTER TABLE [dbo].[tbl_order] CHECK CONSTRAINT [FK_tbl_order_tbl_customer]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_order]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_shoes] FOREIGN KEY([productID])
REFERENCES [dbo].[tbl_shoes] ([shoesID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_shoes]
GO
ALTER TABLE [dbo].[tbl_shoeBySize]  WITH CHECK ADD  CONSTRAINT [FK_tbl_shoeBySize_tbl_shoes] FOREIGN KEY([shoeID])
REFERENCES [dbo].[tbl_shoes] ([shoesID])
GO
ALTER TABLE [dbo].[tbl_shoeBySize] CHECK CONSTRAINT [FK_tbl_shoeBySize_tbl_shoes]
GO
ALTER TABLE [dbo].[tbl_shoeBySize]  WITH CHECK ADD  CONSTRAINT [FK_tbl_shoeBySize_tbl_sizes] FOREIGN KEY([sizeID])
REFERENCES [dbo].[tbl_sizes] ([id])
GO
ALTER TABLE [dbo].[tbl_shoeBySize] CHECK CONSTRAINT [FK_tbl_shoeBySize_tbl_sizes]
GO
