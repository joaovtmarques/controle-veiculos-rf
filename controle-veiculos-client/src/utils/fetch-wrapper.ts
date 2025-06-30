export async function fetchWrapper<T = unknown>(
  input: RequestInfo | URL,
  init?: RequestInit | undefined,
  asText = false,
  asBlob = false
) {
  let result;

  try {
    const response = await fetch(`http://localhost:8080/api/v1/${input}`, init);

    if (asBlob) {
      result = await response.blob();
    } else if (asText) {
      result = await response.text();
    } else {
      result = await response.json();
    }

    if (!response.ok) {
      throw new Error(`Erro na requisição: ${response.statusText}`);
    }
  } catch (error) {
    console.error("Erro na requisição:", error);
    throw error;
  }

  return result as T;
}
