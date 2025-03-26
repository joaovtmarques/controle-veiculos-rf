import { RegistrationForm } from "@/components/form";
import Image from "next/image";

export default function Page() {
  return (
    <div>
      <header
        className="h-32 w-full bg-primary relative after:content-[''] 
    after:absolute after:left-0 after:-bottom-[1.3rem] after:w-full 
    after:h-[1.3rem] after:bg-secondary"
      >
        <div className="h-full flex items-center justify-center gap-x-7">
          <Image
            src="/favicon.png"
            alt="6º Batalhão de Infantaria Aeromóvel"
            width={62}
            height={77}
          />
          <div>
            <h4 className="text-sm font-normal text-background">
              Seção de Informática
            </h4>
            <h1 className="text-[28px] font-bold text-background">
              6º Batalhão de Infantaria Aeromóvel
            </h1>
          </div>
        </div>
      </header>

      <main className="h-full w-full px-36 py-10 items-center justify-center bg-background">
        <RegistrationForm />
      </main>
    </div>
  );
}
