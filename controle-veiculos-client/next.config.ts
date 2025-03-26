import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  webpack: (config, { isServer }) => {
    config.module.rules.push({
      test: /\.(pdf)$/,
      use: [
        {
          loader: "file-loader",
          options: {
            publicPath: "/_next/static/files",
            outputPath: `${isServer ? "../" : ""}static/files`,
            name: "[name].[ext]",
          },
        },
      ],
    });

    return config;
  },
};

export default nextConfig;
