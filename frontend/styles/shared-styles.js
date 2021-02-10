// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
<style include='lumo-badge'>
        html {
      --lumo-font-size: 1rem;
      --lumo-font-size-xxxl: 3rem;
      --lumo-font-size-xxl: 2.25rem;
      --lumo-font-size-xl: 1.75rem;
      --lumo-font-size-l: 1.375rem;
      --lumo-font-size-m: 1.125rem;
      --lumo-font-size-s: 1rem;
      --lumo-font-size-xs: 0.875rem;
      --lumo-font-size-xxs: 0.8125rem;

    }

</style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
