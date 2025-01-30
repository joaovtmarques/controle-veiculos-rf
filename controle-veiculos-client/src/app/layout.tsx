import "./globals.css";

import type { Metadata } from "next";
import { Open_Sans } from "next/font/google";
import { Toaster } from "sonner";

const openSans = Open_Sans({
  weight: ["400", "500", "700"],
  style: ["normal"],
  subsets: ["latin"],
  display: "swap",
});

export const metadata: Metadata = {
  title: "Controle de Veículos - Forte Ipiranga",
  description: "Cadastro de veículo para estacionamento no Forte Ipiranga",
  icons: "favicon.png",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`${openSans.className} light select-none`}>
        {children}
        <Toaster theme="light" />
      </body>
    </html>
  );
}
