import type { Stamp } from "@/models/stamp";
import { fetchWrapper } from "@/utils/fetch-wrapper";

export const createStamp = async (userId: number, vehicleId: number) => {
  const response = await fetchWrapper<Promise<Stamp>>(`stamps`, {
    method: "POST",
    body: JSON.stringify({
      userId: Number(userId),
      vehicleId: Number(vehicleId),
    }),
    headers: new Headers({
      "content-type": "application/json",
    }),
  });

  return response;
};
