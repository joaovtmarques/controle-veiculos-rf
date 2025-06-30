import type { Vehicle } from "@/models/vehicle";
import type { FormData } from "@/schemas/form-schema";
import { fetchWrapper } from "@/utils/fetch-wrapper";

export const createVehicle = async (data: FormData, userId: number) => {
  const response = await fetchWrapper<Promise<Vehicle>>(`vehicles`, {
    method: "POST",
    body: JSON.stringify({
      userId: userId,
      model: data.model,
      plate: data.plate,
      color: data.color,
      type: data.type,
      licensing: data.licensing,
    }),
    headers: new Headers({
      "content-type": "application/json",
    }),
  });

  return response;
};
