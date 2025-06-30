import type { User } from "@/models/user";
import type { FormData } from "@/schemas/form-schema";
import { fetchWrapper } from "@/utils/fetch-wrapper";

export const createUser = async (data: FormData) => {
  const response = await fetchWrapper<Promise<User>>(`users`, {
    method: "POST",
    body: JSON.stringify({
      name: data.name,
      rank: data.rank,
      warName: data.warName,
      phoneNumber: data.phoneNumber,
      company: data.company,
      cpf: data.cpf,
      email: data.email,
      driverLicenseExpiration: data.driverLicenseExpiration,
    }),
    headers: new Headers({
      "content-type": "application/json",
    }),
  });

  return response;
};
