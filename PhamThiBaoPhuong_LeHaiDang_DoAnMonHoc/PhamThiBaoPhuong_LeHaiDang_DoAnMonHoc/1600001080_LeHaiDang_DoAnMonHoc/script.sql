USE [qlBanCafe]
GO
/****** Object:  StoredProcedure [dbo].[sp_showHoaDon]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[sp_showHoaDon]
as
SELECT a.MaHD,TenThucUong,Soluong,DonGia,Soluong*DonGia as N'TongTien',Ngay
FROM	HoaDon a, ChiTietHoaDon b, ThucUong c
WHERE a.MaHD = b.MaHD and b.MaTU = c.MaTU 
Order by a.MaHD desc

GO
/****** Object:  StoredProcedure [dbo].[sp_showHoaDontheoMaHD]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[sp_showHoaDontheoMaHD] @maHD int
as
SELECT a.MaHD,TenThucUong,Soluong,DonGia,Soluong*DonGia as N'TongTien',Ngay
FROM	HoaDon a, ChiTietHoaDon b, ThucUong c
WHERE a.MaHD = b.MaHD and b.MaTU = c.MaTU and a.MaHD = @maHD

GO
/****** Object:  StoredProcedure [dbo].[sp_SoLuongMon]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[sp_SoLuongMon] @tuNgay nvarchar(10), @denNgay nvarchar(10)
AS
select ThucUong.MaTU,TenThucUong,sum(Soluong) as N'Số Lượng'
from HoaDon, ChiTietHoaDon, ThucUong, Loai
where HoaDon.MaHD = ChiTietHoaDon.MaHD 
and ThucUong.MaTU = ChiTietHoaDon.MaTU 
and Loai.MaLoai = ThucUong.MaLoai
and Ngay between @tuNgay and @denNgay
group by ThucUong.MaTU, TenThucUong
order by sum(Soluong) desc

GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[MaCT] [int] IDENTITY(1,1) NOT NULL,
	[MaHD] [int] NOT NULL,
	[MaTU] [int] NOT NULL,
	[Soluong] [int] NOT NULL,
 CONSTRAINT [PK_CHITIET] PRIMARY KEY CLUSTERED 
(
	[MaCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [int] IDENTITY(1,1) NOT NULL,
	[Ngay] [datetime] NOT NULL,
	[TongTien] [int] NOT NULL,
	[MaKM] [int] NOT NULL,
 CONSTRAINT [PK_HoaDon_MaHD] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhuyenMai](
	[MaKM] [int] IDENTITY(1,1) NOT NULL,
	[TenKM] [varchar](5) NOT NULL,
	[PhanTram] [int] NOT NULL,
	[NoiDung] [nvarchar](50) NULL,
 CONSTRAINT [PK_KhuyenMai_MaKM] PRIMARY KEY CLUSTERED 
(
	[MaKM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Loai]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai](
	[MaLoai] [int] IDENTITY(1,1) NOT NULL,
	[TenLoai] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Loai_MaLoai] PRIMARY KEY CLUSTERED 
(
	[MaLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NguoiDung]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NguoiDung](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](20) NOT NULL,
	[pass] [varchar](50) NOT NULL,
	[Hoten] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NguoiDung] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ThucUong]    Script Date: 1/10/2019 9:31:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThucUong](
	[MaTU] [int] IDENTITY(1,1) NOT NULL,
	[TenThucUong] [nvarchar](50) NOT NULL,
	[DonGia] [int] NOT NULL,
	[MaLoai] [int] NOT NULL,
 CONSTRAINT [PK_ThucUong_MaTU] PRIMARY KEY CLUSTERED 
(
	[MaTU] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[ChiTietHoaDon] ON 

INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (1, 1, 1, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (2, 1, 12, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (3, 1, 20, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (4, 2, 10, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (5, 2, 11, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (6, 2, 15, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (7, 3, 18, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (8, 3, 13, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (9, 3, 5, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (10, 3, 7, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (11, 4, 8, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (12, 4, 3, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (13, 5, 12, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (14, 5, 10, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (15, 5, 19, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (16, 5, 14, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (17, 6, 17, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (18, 6, 20, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (19, 7, 18, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (20, 8, 6, 2)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (21, 8, 7, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (22, 9, 19, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (23, 10, 14, 1)
INSERT [dbo].[ChiTietHoaDon] ([MaCT], [MaHD], [MaTU], [Soluong]) VALUES (24, 11, 5, 2)
SET IDENTITY_INSERT [dbo].[ChiTietHoaDon] OFF
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (1, CAST(0x0000AAD900000000 AS DateTime), 172900, 2)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (2, CAST(0x0000AAD900000000 AS DateTime), 156000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (3, CAST(0x0000AAD900000000 AS DateTime), 286200, 3)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (4, CAST(0x0000AAD900000000 AS DateTime), 100000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (5, CAST(0x0000AAD900000000 AS DateTime), 250800, 2)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (6, CAST(0x0000AAD900000000 AS DateTime), 177000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (7, CAST(0x0000AAD900000000 AS DateTime), 65000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (8, CAST(0x0000AAD900000000 AS DateTime), 115000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (9, CAST(0x0000AAD900000000 AS DateTime), 59000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (10, CAST(0x0000AAD900000000 AS DateTime), 59000, 1)
INSERT [dbo].[HoaDon] ([MaHD], [Ngay], [TongTien], [MaKM]) VALUES (11, CAST(0x0000AAD900000000 AS DateTime), 90000, 1)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
SET IDENTITY_INSERT [dbo].[KhuyenMai] ON 

INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTram], [NoiDung]) VALUES (1, N'KM000', 0, N'Không KM')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTram], [NoiDung]) VALUES (2, N'KM001', 5, N'Ăn mừng VN thua!')
INSERT [dbo].[KhuyenMai] ([MaKM], [TenKM], [PhanTram], [NoiDung]) VALUES (3, N'KMTet', 10, N'Khuyến mại tết')
SET IDENTITY_INSERT [dbo].[KhuyenMai] OFF
SET IDENTITY_INSERT [dbo].[Loai] ON 

INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (1, N'Cà phê')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (2, N'Trà và Macchiato')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (3, N'Thức uống đá xay')
INSERT [dbo].[Loai] ([MaLoai], [TenLoai]) VALUES (4, N'Khuyến mãi tết')
SET IDENTITY_INSERT [dbo].[Loai] OFF
SET IDENTITY_INSERT [dbo].[NguoiDung] ON 

INSERT [dbo].[NguoiDung] ([ID], [username], [pass], [Hoten]) VALUES (1, N'admin1', N'admin1', N'Lê Hải Đăng')
INSERT [dbo].[NguoiDung] ([ID], [username], [pass], [Hoten]) VALUES (2, N'admin2', N'admin2', N'Phạm Thị Bảo Phương')
SET IDENTITY_INSERT [dbo].[NguoiDung] OFF
SET IDENTITY_INSERT [dbo].[ThucUong] ON 

INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (1, N'Americano', 39000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (2, N'Bạc sỉu', 29000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (3, N'Cà phê đen', 29000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (4, N'Cappucinno', 45000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (5, N'Latte', 45000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (6, N'Espresso', 35000, 1)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (7, N'Trà đào cam sả', 45000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (8, N'Trà đen Macchiato', 42000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (9, N'Trà gạo rang Macchiato', 48000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (10, N'Trà kim quất trân châu', 42000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (11, N'Trà matcha latte', 55000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (12, N'Trà vải cam dâu', 45000, 2)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (13, N'Caramel đá xay', 59000, 3)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (14, N'Cookies đá xay', 59000, 3)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (15, N'Matcha đá xay', 59000, 3)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (16, N'Mocha đá xay', 59000, 3)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (17, N'Sô cô la đá xay', 59000, 3)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (18, N'Nước ép cam tươi', 65000, 4)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (19, N'Sinh tố cam xoài', 59000, 4)
INSERT [dbo].[ThucUong] ([MaTU], [TenThucUong], [DonGia], [MaLoai]) VALUES (20, N'Sinh tố việt quất', 59000, 4)
SET IDENTITY_INSERT [dbo].[ThucUong] OFF
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CHITIET_MaHD] FOREIGN KEY([MaHD])
REFERENCES [dbo].[HoaDon] ([MaHD])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_CHITIET_MaHD]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK_CHITIET_MaTU] FOREIGN KEY([MaTU])
REFERENCES [dbo].[ThucUong] ([MaTU])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_CHITIET_MaTU]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon_MaKM] FOREIGN KEY([MaKM])
REFERENCES [dbo].[KhuyenMai] ([MaKM])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_MaKM]
GO
ALTER TABLE [dbo].[ThucUong]  WITH CHECK ADD  CONSTRAINT [FK_ThucUong_MaLoai] FOREIGN KEY([MaLoai])
REFERENCES [dbo].[Loai] ([MaLoai])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ThucUong] CHECK CONSTRAINT [FK_ThucUong_MaLoai]
GO
